import React, { Component } from "react";
import OvAudio from "./OvAudio";

export default class OvComponent extends Component {
  render() {
    return (
      <div>
        {this.props.streamManager !== undefined ? (
          <div className="streamcomponent">
            <OvAudio streamManager={this.props.streamManager} />
          </div>
        ) : null}
      </div>
    );
  }
}
